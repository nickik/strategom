(ns strategom.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defn count-units [board color]
  (frequencies
   (filter identity (map (fn [[key {:keys [unit]}]]
                           (when (= (:color unit)
                                    color)
                             (:type unit)))
                         board))))

(defn count-unit [board type color]
  (type (count-units board color)))

(def app-state (atom {:test "1234"
                      :board {[2 1] {:ground :land, :unit {:type :lieutenant, :color :red}},
                              [3 2] {:ground :land, :unit {:type :sergeant, :color :red}},
                              [4 3] {:ground :water, :unit {:type nil, :color nil}},
                              [5 4] {:ground :land, :unit {:type nil, :color nil}},
                              [6 5] {:ground :land, :unit {:type :scout, :color :blue}}, [7 6] {:ground :land, :unit {:type :scout, :color :blue}},
                              [8 7] {:ground :land, :unit {:type :miner, :color :blue}}, [9 8] {:ground :land, :unit {:type :scout, :color :blue}},
                              [1 0] {:ground :land, :unit {:type :scout, :color :red}}, [2 2] {:ground :land, :unit {:type :miner, :color :red}},
                              [3 3] {:ground :land, :unit {:type :miner, :color :red}}, [4 4] {:ground :land, :unit {:type nil, :color nil}},
                              [5 5] {:ground :land, :unit {:type nil, :color nil}}, [6 6] {:ground :land, :unit {:type :major, :color :blue}},
                              [7 7] {:ground :land, :unit {:type :bomb, :color :blue}}, [8 8] {:ground :land, :unit {:type :scout, :color :blue}},
                              [9 9] {:ground :land, :unit {:type :lieutenant, :color :blue}}, [0 0] {:ground :land, :unit {:type :lieutenant, :color :red}},
                              [1 1] {:ground :land, :unit {:type :captain, :color :red}}, [2 3] {:ground :land, :unit {:type :scout, :color :red}},
                              [3 4] {:ground :land, :unit {:type :major, :color :red}}, [4 5] {:ground :land, :unit {:type nil, :color nil}},
                              [5 6] {:ground :water, :unit {:type nil, :color nil}}, [6 7] {:ground :land, :unit {:type :captain, :color :blue}},
                              [7 8] {:ground :land, :unit {:type :captain, :color :blue}}, [8 9] {:ground :land, :unit {:type :miner, :color :blue}},
                              [0 1] {:ground :land, :unit {:type :sergeant, :color :red}}, [1 2] {:ground :land, :unit {:type :scout, :color :red}},
                              [2 4] {:ground :land, :unit {:type :scout, :color :red}}, [3 5] {:ground :land, :unit {:type :bomb, :color :red}},
                              [4 6] {:ground :water, :unit {:type nil, :color nil}}, [5 7] {:ground :water, :unit {:type nil, :color nil}},
                              [6 8] {:ground :land, :unit {:type :flag, :color :blue}}, [7 9] {:ground :land, :unit {:type :sergeant, :color :blue}},
                              [0 2] {:ground :land, :unit {:type :marshal, :color :red}}, [1 3] {:ground :land, :unit {:type :scout, :color :red}},
                              [2 5] {:ground :land, :unit {:type :major, :color :red}}, [3 6] {:ground :land, :unit {:type :sergeant, :color :red}},
                              [4 7] {:ground :water, :unit {:type nil, :color nil}}, [5 8] {:ground :land, :unit {:type nil, :color nil}},
                              [6 9] {:ground :land, :unit {:type :lieutenant, :color :blue}}, [0 3] {:ground :land, :unit {:type :spy, :color :red}},
                              [1 4] {:ground :land, :unit {:type :scout, :color :red}}, [2 6] {:ground :land, :unit {:type :scout, :color :red}},
                              [3 7] {:ground :land, :unit {:type :captain, :color :red}}, [4 8] {:ground :land, :unit {:type nil, :color nil}},
                              [5 9] {:ground :land, :unit {:type nil, :color nil}}, [0 4] {:ground :land, :unit {:type :colonel, :color :red}},
                              [1 5] {:ground :land, :unit {:type :colonel, :color :red}}, [2 7] {:ground :land, :unit {:type :general, :color :red}},
                              [3 8] {:ground :land, :unit {:type :bomb, :color :red}}, [4 9] {:ground :land, :unit {:type nil, :color nil}},
                              [0 5] {:ground :land, :unit {:type :major, :color :red}}, [1 6] {:ground :land, :unit {:type :flag, :color :red}},
                              [2 8] {:ground :land, :unit {:type :bomb, :color :red}}, [3 9] {:ground :land, :unit {:type :miner, :color :red}},
                              [0 6] {:ground :land, :unit {:type :bomb, :color :red}}, [1 7] {:ground :land, :unit {:type :bomb, :color :red}},
                              [2 9] {:ground :land, :unit {:type :miner, :color :red}}, [0 7] {:ground :land, :unit {:type :scout, :color :red}},
                              [1 8] {:ground :land, :unit {:type :miner, :color :red}}, [0 8] {:ground :land, :unit {:type :lieutenant, :color :red}},
                              [1 9] {:ground :land, :unit {:type :captain, :color :red}}, [0 9] {:ground :land, :unit {:type :bomb, :color :red}},
                              [9 0] {:ground :land, :unit {:type :bomb, :color :blue}}, [8 0] {:ground :land, :unit {:type :sergeant, :color :blue}},
                              [9 1] {:ground :land, :unit {:type :sergeant, :color :blue}}, [7 0] {:ground :land, :unit {:type :lieutenant, :color :blue}},
                              [8 1] {:ground :land, :unit {:type :lieutenant, :color :blue}}, [9 2] {:ground :land, :unit {:type :major, :color :blue}},
                              [6 0] {:ground :land, :unit {:type :miner, :color :blue}}, [7 1] {:ground :land, :unit {:type :bomb, :color :blue}},
                              [8 2] {:ground :land, :unit {:type :spy, :color :blue}}, [9 3] {:ground :land, :unit {:type :miner, :color :blue}},
                              [5 0] {:ground :land, :unit {:type nil, :color nil}}, [6 1] {:ground :land, :unit {:type :scout, :color :blue}},
                              [7 2] {:ground :land, :unit {:type :scout, :color :blue}}, [8 3] {:ground :land, :unit {:type :colonel, :color :blue}},
                              [9 4] {:ground :land, :unit {:type :bomb, :color :blue}}, [4 0] {:ground :land, :unit {:type nil, :color nil}},
                              [5 1] {:ground :land, :unit {:type nil, :color nil}}, [6 2] {:ground :land, :unit {:type :bomb, :color :blue}},
                              [7 3] {:ground :land, :unit {:type :colonel, :color :blue}}, [8 4] {:ground :land, :unit {:type :scout, :color :blue}},
                              [9 5] {:ground :land, :unit {:type :marshal, :color :blue}}, [3 0] {:ground :land, :unit {:type :lieutenant, :color :red}},
                              [4 1] {:ground :land, :unit {:type nil, :color nil}}, [5 2] {:ground :water, :unit {:type nil, :color nil}},
                              [6 3] {:ground :land, :unit {:type :bomb, :color :blue}}, [7 4] {:ground :land, :unit {:type :miner, :color :blue}},
                              [8 5] {:ground :land, :unit {:type :scout, :color :blue}}, [9 6] {:ground :land, :unit {:type :sergeant, :color :blue}},
                              [3 1] {:ground :land, :unit {:type :captain, :color :red}}, [4 2] {:ground :water, :unit {:type nil, :color nil}},
                              [5 3] {:ground :water, :unit {:type nil, :color nil}}, [6 4] {:ground :land, :unit {:type :captain, :color :blue}},
                              [7 5] {:ground :land, :unit {:type :major, :color :blue}}, [8 6] {:ground :land, :unit {:type :captain, :color :blue}},
                              [9 7] {:ground :land, :unit {:type :general, :color :blue}}, [2 0] {:ground :land, :unit {:type :sergeant, :color :red}}}}))

(defn unit-row [[unit-type n] bgc]
    (let [st #js {:backgroundColor bgc}]
      (dom/tr #js {:className "units" :style st}
              (dom/td nil (str unit-type))
              (dom/td nil (str n)))))

(defn renter-unit-table [units-alive-counts]
  (apply dom/table #js {:className "units"}
         (map unit-row
              units-alive-counts
              (cycle ["#f90" "#fff"]))))


(defn render-tile [board pos]
  (let [tile (get board pos)
        unit (:unit tile)]
    (dom/td (if (= :land (:ground tile))
              #js {:className "tile" :style  #js {:backgroundColor "#0c0"}}
              #js {:className "tile" :style  #js {:backgroundColor "#00f"}})
            (if (:color unit)
              (cond
               (= (:color unit) :blue) (str (:type unit))
               (= (:color unit) :red) (str "red")
               :else "")
              ""))))

(defn render-row [board positions]
  (apply dom/tr nil
         (map #(render-tile board %) positions)))

(defn stratego-app [data owner]
  (reify
    om/IRender
    (render [this]
            (dom/div nil
               (dom/header #js {:id "header"})
               (dom/h1 nil "Stratego")
               (dom/div #js {:id "unit-table"}
                        (renter-unit-table (count-units (:board data) :blue)))
               (dom/div #js {:id "board"}
                        (apply dom/table #js {:className "board"}
                               (map #(render-row (:board data) %)
                                    (partition 10 (sort (keys (:board data)))))))))))

(om/root
 app-state
 stratego-app
 (. js/document (getElementById "stratego-app")))


(defn apply-to-board [a f & args]
  (let [b (:board current)]
     (assoc current :board (apply f b args))))

(defn set-unit [field pos unit]
  (assoc-in field [pos :unit] unit))

(comment (swap! app-state assoc-in [:board [5 1] :unit] {:type :flag :color :blue}))

(def transition-board (partial swap! app-state apply-to-board))

(comment (transition-board set-unit [5 1] {:unit :flag :color :blue}))
