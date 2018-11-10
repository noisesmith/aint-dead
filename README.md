# aint-dead

A bare minimum http server to serve up a health check response.

## Usage

```
user=> (require '[org.noisesmith.aint.dead :as aint])
nil
user=> (aint/yet 8000 "/foo" (constantly {:body "hi" :status 200}))
#object[sun.net.httpserver.HttpServerImpl 0x22ae9c4f "sun.net.httpserver.HttpServerImpl@22ae9c4f"]
user=> (slurp "http://localhost:8000/foo")
"hi"

```


## License

Copyright © 2018 Justin Glenn Smith noisesmith@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
