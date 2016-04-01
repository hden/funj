(ns funj.core)

(defonce ^:private cache (atom {}))

(defn wrap
  "Takes a function f and fewer than the normal arguments to f, and
  returns a fn that takes a variable number of additional args. When
  called, the returned function calls f with args + additional args."
  {:static true}
  [f & bindings]
  (let [bound (apply partial (cons f bindings))]
    (swap! cache assoc bound f)
    bound))

(defn unwrap
  "Takes a wrapped function f and returns the original function."
  {:static true}
  [f]
  (get @cache f f))

(defn call
  "Applies fn f to the argument list formed by prepending intervening arguments to args."
  {:static true}
  [f & args]
  (apply (unwrap f) args))
