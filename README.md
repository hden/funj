# funj [![Circle CI](https://circleci.com/gh/hden/funj.svg?style=svg)](https://circleci.com/gh/hden/funj)

A Clojure library designed to create a simple dependency injection module for
functions. This makes writing unit tests a breeze.

Ported from https://github.com/tusharmath/funjector

## Usage

### core.clj

```clojure
(defn c [x]
  (- x 1))

(defn ^:private -b [c x]
  (c (* x 100)))

(def b (wrap -b c)) ;; Binds the function B with C as the first param

(defn ^:private -a [b x y]
  (b (+ x y)))

(def a (wrap -a b)) ;; Binds the function A with B as the first param
```

### core_test.clj

```clojure
(testing "integration"
  ;; Calls the partialized version of the function
  (is (= (a 10 20) 2999)))

(testing "unit test"
  ;; Calls the original version of function A with a custom implementation of B
  (let [orig (unwrap a)]
    (is (= (orig + 10 20) 31))))
```

## API

### (wrap f & args)
Creates a function that calls f with args arguments prepended to those provided to the new function.

```clojure
(def a (wrap * 10))
(a 3) ;; 30
(a 4) ;; 40
```

### (unwrap f)
Returns the original version of the partialized function.

```clojure
(def mul [x y] (* x y))
(def mul10 (wrap mul 10))
(= (unwrap mul10) mul) ;; true
```

## How is this different then [clojure.core/partial](http://clojuredocs.org/clojure.core/partial)?
As a matter of fact it isn't!
The only added functionality is that you have more control over when to use the partialized version.

## License

MIT
