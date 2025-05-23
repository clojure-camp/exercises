{:title
 {:en-US "bank"
  :pt-BR "banco"}
 :category :synthesis
 :difficulty :high
 :instructions
 {:en-US ["Implement an in-memory bank system, which keeps track of multiple accounts. Accounts can be created, and each account supports make withdrawals, deposits and transfers, and retrieving the current balance and a log of transactions."
          "Withdrawals and transfers should only occur when there is a sufficient balance. Otherwise, throw an error."
          "Prevent accounts from being re-created."
          "Withdrawals, deposits and transfers should throw an error if an incorrect id is given."
          "Transactions should include: time, type and amount."
          "You are free to come up with your own names for functions, what values they take and what they return."
          "Write your own tests. Also test for errors being thrown."]
  :pt-BR ["Implemente um sistema bancário em memória, que mantém o controle de múltiplas contas. Contas podem ser criadas, e cada conta suporta saques, depósitos e transferências, e recuperar o saldo atual e um log de transações."
          "Saques e transferências só devem ocorrer quando houver saldo suficiente. Caso contrário, lance um erro."
          "Evite que contas sejam recriadas."
          "Saques, depósitos e transferências devem lançar um erro se um id incorreto for fornecido."
          "Transações devem incluir: tempo, tipo e valor."
          "Você é livre para criar seus próprios nomes para funções, quais valores elas recebem e o que retornam."
          "Escreva seus próprios testes. Teste também se erros são lançados."]}

 :test-cases []

 :uses #{:atoms :data :math-operations :keywords :testing
         defn atom thrown? throw ex-info select-keys swap!
         update update-in java.util.Date not contains?
         conj -> ->>}

 :source "https://exercism.io/tracks/clojure/exercises/bank-account"
 }

;; --- [:solution 0]

(def accounts (atom {}))

(defn exists? [id]
  (contains? @accounts id))

(defn balance [id]
  (get-in @accounts [id :balance]))

(defn transactions [id]
  (get-in @accounts [id :transactions]))

(defn create-account! [id]
  (if (exists? id)
    (throw (ex-info "Account with this id already exists" {:account-id id}))
    (swap! accounts assoc id {:balance 0
                              :transactions []})))

(defn deposit! [id amount]
  (if (not (exists? id))
    (throw (ex-info "Account with this id does not exist" {:account-id id}))
    (swap! accounts update id
           (fn [account]
             (-> account
                 (update :balance + amount)
                 (update :transactions conj {:time (java.util.Date.)
                                             :type :deposit
                                             :amount amount}))))))

(defn withdraw! [id amount]
  (cond
    (not (exists? id))
    (throw (ex-info "Account with this id does not exist" {:account-id id}))
    (< (balance id) amount)
    (throw (ex-info "Insufficient balance to withdraw given amount" {:account-id id}))
    :else
    (swap! accounts update id
           (fn [account]
             (-> account
                 (update :balance - amount)
                 (update :transactions conj {:time (java.util.Date.)
                                             :type :withdrawal
                                             :amount amount}))))))

(defn transfer! [from-id to-id amount]
  (cond
    (not (exists? from-id))
    (throw (ex-info "Account with this id does not exist" {:account-id from-id}))
    (not (exists? to-id))
    (throw (ex-info "Account with this id does not exist" {:account-id to-id}))
    (< (balance from-id) amount)
    (throw (ex-info "Insufficient balance to perform transfer" {:account-id from-id}))
    :else
    (swap! accounts (fn [accounts]
                      (-> accounts
                          (update-in [from-id :balance] - amount)
                          (update-in [from-id :transactions] conj {:time (java.util.Date.)
                                                                   :type :transfer-out
                                                                   :amount amount})
                          (update-in [to-id :balance] + amount)
                          (update-in [to-id :transactions] conj {:time (java.util.Date.)
                                                                 :type :transfer-in
                                                                 :amount amount}))))))

;; tests included here to not bias your own implementation choices
(do (is (thrown? Exception (deposit! :a 100)))
    (is (thrown? Exception (withdraw! :a 100)))
    (is (thrown? Exception (transfer! :a :b 100)))
    (create-account! :a)
    (create-account! :b)
    (is (thrown? Exception (create-account! :a)))
    (deposit! :a 100)
    (withdraw! :a 50)
    (is (thrown? Exception (withdraw! :a 100)))
    (is (thrown? Exception (transfer! :a :b 100)))
    (transfer! :a :b 50)
    (is (= 0 (balance :a)))
    (is (= 50 (balance :b)))
    (is (= [{:type :deposit
             :amount 100}
            {:type :withdrawal
             :amount 50}
            {:type :transfer-out
             :amount 50}]
           (map (fn [tx]
                  (select-keys tx [:type :amount]))
                (transactions :a))))
    (is (= [{:type :transfer-in
             :amount 50}]
           (map (fn [tx]
                  (select-keys tx [:type :amount]))
                (transactions :b)))))
