(ns org.noisesmith.aint.dead
  (:import (java.net InetSocketAddress)
           (com.sun.net.httpserver HttpExchange
                                   HttpHandler
                                   HttpServer)))

(defn handler
  [status-fn]
    (proxy [HttpHandler] []
      (handle [^HttpExchange t]
        (let [{:keys [status body]} (status-fn)
              payload (.getBytes body "UTF-8")]
        (.sendResponseHeaders t status (count payload))
        (doto (.getResponseBody t)
          (.write payload)
          (.close))
        nil))))

(defn yet
  "I ATEN'T DEAD
  -- Granny Weatherwax

  port - a port number to server from
  path - the route to serve on that port
  status-fn - should return a hash-map of {:status Long :body String}"
  [port path status-fn]
  (let [server (HttpServer/create (InetSocketAddress. port) 0)
        handle (handler status-fn)]
    (doto server
      (.createContext path handle)
      (.setExecutor nil)
      (.start))))
