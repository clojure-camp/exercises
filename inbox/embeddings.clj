(ns embeddings
  (:require
    [cheshire.core :as json]
    [org.httpkit.client :as http]))

(defn openai-embedding [query]
  (-> @(http/request {:url "https://api.openai.com/v1/embeddings"
                      :method :post
                      :headers {"Content-Type" "application/json"
                                "Authorization" (str "Bearer "
                                                     (slurp "open-ai-key"))}
                      :body (json/encode {:input query
                                          :model "text-embedding-3-small"})})
       :body
       (json/decode)
       (get "data")
       first
       (get "embedding")))

(defonce index (atom {}))

(defn distance [va vb]
  (Math/sqrt (apply + (map (fn [a b]
                             (Math/pow (- a b) 2)) va vb))))

(distance [1 1] [0 2])




;; create two functions
;; index() and search()
;; index() takes some text, calculates the embedding and stores it
;; search() takes a query and returns the top-n closest texts to it (by embedding)

(defn index! [text])

(defn search [query top-n])
