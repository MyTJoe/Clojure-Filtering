(ns clojure-filtering.core
(:require [clojure.string :as str])
(:require [clojure.data.json :as json])
(:gen-class))

(defn -main []
  (println "Type a category:")

  (let [purchases (slurp "purchases.csv") ; takes in the csv
        purchases (str/split-lines purchases) ; split by line?
        purchases (map (fn [line]
                         (str/split line #","))
                       purchases)
        header (first purchases)
        purchases (rest purchases)
        purchases (map (fn [line]
                         (zipmap header line))
                       purchases)
        category (read-line)
        purchases (filter (fn [line]
                            (= (get line "category") category))
                          purchases)
        file-json (json/write-str purchases)]
    (spit (str "filtered_list_" category ".json") file-json) category))
