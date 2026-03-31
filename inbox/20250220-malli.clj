(ns exercises.20250220
  (:require [malli.core :as m]
            [malli.error :as me]
            [malli.transform :as mt]))

;; using malli to validate:
;; ring http request

(def incoming-req
  {:server-port 8080
   :server-name "localhost"
   :remote-addr "127.0.0.1"
   :uri "/api/users"
   :query-string "id=123&name=test"
   :scheme :http
   :request-method :get
   :body "..."
   :headers {"host" "localhost:8080"
             "user-agent" "Mozilla/5.0"
             "accept" "application/json"
             "content-type" "application/json"}
   ;; these are from middleware:
   :body-params {:user-id 123}
   :params {:id "123"
            :name 1
            :user-id 123}})

(def uri-re #"/.*")

(def Request
  [:map
   {:closed false}
   [:server-port
    [:and [:fn even?] [:int {:min 1 :max 65535}]]]
   [:server-name :string]
   [:remote-addr :string]
   [:headers [:map-of :string :string]]
   [:scheme [:enum :http :https]]
   [:request-method [:enum :connect :trace :post :get :put :patch :delete :head :options]]
   [:query-string :string]
   [:uri [:re uri-re]]
   [:params {:optional true}
    [:map-of :keyword [:or :string :int]]]])

(m/validate Request incoming-req)

(m/explain Request incoming-req)

(try
  (m/assert Request incoming-req)
  (catch Exception e
    (-> e ex-data :data :explain me/humanize)))

(def incoming-uncoerced-req
  {:server-port "8080"
   :server-name "localhost"
   :remote-addr "127.0.0.1"
   :uri "/api/users"
   :query-string "id=123&name=test"
   :scheme :http
   :request-method "get"
   :body "..."
   :headers {"host" "localhost:8080"
             "user-agent" "Mozilla/5.0"
             "accept" "application/json"
             "content-type" "application/json"}
   ;; these are from middleware:
   :body-params {:user-id 123}
   :params {:id "123"
            :name 1
            :user-id 123}})

((m/decoder Request mt/string-transformer)
 incoming-uncoerced-req)

;; homework challenge: schema for hiccup
