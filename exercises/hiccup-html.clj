{:title
 {:en-US "generated HTML"
  :pt-BR "HTML gerado"}
 :instructions
 {:en-US ["Implement a simple version of 'hiccup', a common Clojure format for representing HTML as Clojure data."]
  :pt-BR ["Implemente uma versão simples de 'hiccup', um formato comum em Clojure para representar HTML como dados Clojure."]}
 :function-template (defn hiccup->html [hiccup])
 :test-cases [{:input
               (hiccup->html
                 [:html
                  [:head [:title "Hello world"]]
                  [:body
                   [:div {:id "app"}
                    [:p {:style "color: red"
                         :class "hello"}
                     "Some stuff"]
                    [:ul
                     [:li "foo"]
                     [:li "bar"]]]]])
               :output
               "<html><head><title>Hello world</title></head><body><div id=\"app\"><p class=\"hello\" style=\"color: red\">Some stuff</p><ul><li>foo</li><li>bar</li></ul></div></body></html>"}]
 :uses #{:recursion name apply str map string? vector? cond?}
 :difficulty :mid
 :category :synthesis}

;; --- [:solution 0]

(defn hiccup-attrs
  [attrs]
  (->> attrs
       (map
        (fn [[k v]] (str " " (name k) "=\"" v "\"")))
       sort ; just for a consistent order for testing
       (apply str)))

(defn hiccup->html
  [hiccup]
  (cond
    (string? hiccup) hiccup
    (vector? hiccup) (let [[tag & body] hiccup
                           [?attrs body] (if (map? (first body))
                                           [(first body) (rest body)]
                                           [nil body])]
                       (str
                        "<" (name tag)
                        (when ?attrs (hiccup-attrs ?attrs)) ">"
                        (apply str (map hiccup->html body))
                        "</" (name tag) ">"))))
