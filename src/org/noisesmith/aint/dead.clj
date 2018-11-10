(ns org.noisesmith.aint.dead
  (:import (java.net InetSocketAddress)
           (com.sun.net.httpserver HttpExchange
                                   HttpHandler
                                   HttpServer)))

(defn handler
  [string]
  (let [payload (.getBytes string "UTF-8")]
    (proxy [HttpHandler] []
      (handle [this ^HttpExchange t]
        (.sendResponseHeaders t 200 (count payload))
        (doto (.getResponseBody t)
          (.write payload)
          (.close))
        nil))))

(defn yet
  "antent ded
  -- granny weatherwax"
  []
  (let [server (HttpServer/create (InetSocketAddress. 8000) 0)]
    (doto server
      (.createContext "/foo" (handler "Hi.\n"))
      (.setExecutor nil)
      (.start))))
