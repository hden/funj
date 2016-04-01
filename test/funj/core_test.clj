(ns funj.core-test
  (:require [clojure.test :refer :all]
            [funj.core :refer :all]))

(deftest funj-test
  (testing "wrap"
    (let [f (wrap * 10)]
      (are [x y] (= x y)
           (f 3) 30
           (f 4) 40)))

  (testing "unwrap"
    (let [f (wrap + 10)]
      (is (= (unwrap f) +))))

  (testing "call"
    (let [f (wrap * 10)]
      (are [x y] (= x y)
           (call f 9 3) 27
           (call f 9 4) 36))))
