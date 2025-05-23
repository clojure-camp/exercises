{:title
 {:en-US "modelling todo list"
  :pt-BR "modelando lista de tarefas (todo list)"}
 :difficulty :mid
 :category :starter
 :instructions
 {:en-US ["Devise a data model to represent a multi-user todo list."
          "Next, come up with and write a few helper functions to work with your model, such as:"
          "`incomplete-tasks-for` - given then id of user, returns a list of their incomplete tasks"
          "`completed-tasks` - returns a list of all completed tasks"]
  :pt-BR ["Crie um modelo de dados para representar uma lista de tarefas compartilhada entre vários usuários."
          "Em seguida, crie e escreva algumas funções auxiliares para trabalhar com seu modelo, como:"
          "`incomplete-tasks-for` - dado o id de um usuário, retorna uma lista de suas tarefas incompletas"
          "`completed-tasks` - retorna uma lista de todas as tarefas completas"]}
 :uses #{:maps :vectors :strings :numbers filter}}

;; --- [:solution 0]

(def todo-list-state
  {:users {1 {:name "rafd"}
           2 {:name "jamesnvc"}}
   :tasks [{:id 1
            :completed? false
            :created-user-id 1
            :assigned-user-id 2
            :created-at #inst "2019-10-24T19:47:05-05:00"
            :content "Get everything ready for teaching"}
           {:id 2
            :completed? true
            :created-user-id 2
            :assigned-user-id 2
            :created-at #inst "2019-10-20T19:47:05-05:00"
            :content "Make a bunch of exercises"}
           {:id 3
            :completed? false
            :created-user-id 2
            :assigned-user-id 1
            :created-at #inst "2019-10-27T19:47:05-05:00"
            :content "Teach the class"}]})

(defn incomplete-tasks-for [state user-id]
  (filter (fn [task]
            (= user-id (task :assigned-user-id)))
          (state :tasks)))

(defn completed-tasks [state]
  (filter :completed? (state :tasks)))

