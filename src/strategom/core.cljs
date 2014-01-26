(ns strategom.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def board (atom  {[2 1] {:ground :land, :unit {:color nil, :type nil}},
                   [3 2] {:ground :land, :unit {:color :red, :type :scout}},
                   [4 3] {:ground :water, :unit {:color nil, :type nil}},
                   [5 4] {:ground :land, :unit {:color nil, :type nil}},
                   [6 5] {:ground :land, :unit {:color nil, :type nil}},
                   [7 6] {:ground :land, :unit {:color nil, :type nil}},
                   [8 7] {:ground :land, :unit {:color nil, :type nil}},
                   [9 8] {:ground :land, :unit {:color nil, :type nil}}, [1 0] {:ground :land, :unit {:color nil, :type nil}}, [2 2] {:ground :land, :unit {:color nil, :type nil}}, [3 3] {:ground :land, :unit {:color nil, :type nil}}, [4 4] {:ground :land, :unit {:color nil, :type nil}}, [5 5] {:ground :land, :unit {:color nil, :type nil}}, [6 6] {:ground :land, :unit {:color nil, :type nil}}, [7 7] {:ground :land, :unit {:color nil, :type nil}}, [8 8] {:ground :land, :unit {:color nil, :type nil}}, [9 9] {:ground :land, :unit {:color nil, :type nil}}, [0 0] {:ground :land, :unit {:color nil, :type nil}}, [1 1] {:ground :land, :unit {:color nil, :type nil}}, [2 3] {:ground :land, :unit {:color nil, :type nil}}, [3 4] {:ground :land, :unit {:color nil, :type nil}}, [4 5] {:ground :land, :unit {:color nil, :type nil}}, [5 6] {:ground :water, :unit {:color nil, :type nil}}, [6 7] {:ground :land, :unit {:color nil, :type nil}}, [7 8] {:ground :land, :unit {:color nil, :type nil}}, [8 9] {:ground :land, :unit {:color nil, :type nil}}, [0 1] {:ground :land, :unit {:color nil, :type nil}}, [1 2] {:ground :land, :unit {:color nil, :type nil}}, [2 4] {:ground :land, :unit {:color nil, :type nil}}, [3 5] {:ground :land, :unit {:color nil, :type nil}}, [4 6] {:ground :water, :unit {:color nil, :type nil}}, [5 7] {:ground :water, :unit {:color nil, :type nil}}, [6 8] {:ground :land, :unit {:color nil, :type nil}}, [7 9] {:ground :land, :unit {:color nil, :type nil}}, [0 2] {:ground :land, :unit {:color nil, :type nil}}, [1 3] {:ground :land, :unit {:color nil, :type nil}}, [2 5] {:ground :land, :unit {:color nil, :type nil}}, [3 6] {:ground :land, :unit {:color nil, :type nil}}, [4 7] {:ground :water, :unit {:color nil, :type nil}}, [5 8] {:ground :land, :unit {:color nil, :type nil}}, [6 9] {:ground :land, :unit {:color nil, :type nil}}, [0 3] {:ground :land, :unit {:color nil, :type nil}}, [1 4] {:ground :land, :unit {:color nil, :type nil}}, [2 6] {:ground :land, :unit {:color nil, :type nil}}, [3 7] {:ground :land, :unit {:color nil, :type nil}}, [4 8] {:ground :land, :unit {:color nil, :type nil}}, [5 9] {:ground :land, :unit {:color nil, :type nil}}, [0 4] {:ground :land, :unit {:color nil, :type nil}}, [1 5] {:ground :land, :unit {:color nil, :type nil}}, [2 7] {:ground :land, :unit {:color nil, :type nil}}, [3 8] {:ground :land, :unit {:color nil, :type nil}}, [4 9] {:ground :land, :unit {:color nil, :type nil}}, [0 5] {:ground :land, :unit {:color nil, :type nil}}, [1 6] {:ground :land, :unit {:color nil, :type nil}}, [2 8] {:ground :land, :unit {:color nil, :type nil}}, [3 9] {:ground :land, :unit {:color nil, :type nil}}, [0 6] {:ground :land, :unit {:color nil, :type nil}}, [1 7] {:ground :land, :unit {:color nil, :type nil}}, [2 9] {:ground :land, :unit {:color nil, :type nil}}, [0 7] {:ground :land, :unit {:color nil, :type nil}}, [1 8] {:ground :land, :unit {:color nil, :type nil}}, [0 8] {:ground :land, :unit {:color nil, :type nil}}, [1 9] {:ground :land, :unit {:color nil, :type nil}}, [0 9] {:ground :land, :unit {:color nil, :type nil}}, [9 0] {:ground :land, :unit {:color nil, :type nil}}, [8 0] {:ground :land, :unit {:color nil, :type nil}}, [9 1] {:ground :land, :unit {:color nil, :type nil}}, [7 0] {:ground :land, :unit {:color nil, :type nil}}, [8 1] {:ground :land, :unit {:color nil, :type nil}}, [9 2] {:ground :land, :unit {:color nil, :type nil}}, [6 0] {:ground :land, :unit {:color nil, :type nil}}, [7 1] {:ground :land, :unit {:color nil, :type nil}}, [8 2] {:ground :land, :unit {:color nil, :type nil}}, [9 3] {:ground :land, :unit {:color nil, :type nil}}, [5 0] {:ground :land, :unit {:color nil, :type nil}}, [6 1] {:ground :land, :unit {:color nil, :type nil}}, [7 2] {:ground :land, :unit {:color nil, :type nil}}, [8 3] {:ground :land, :unit {:color nil, :type nil}}, [9 4] {:ground :land, :unit {:color nil, :type nil}}, [4 0] {:ground :land, :unit {:color nil, :type nil}}, [5 1] {:ground :land, :unit {:color nil, :type nil}}, [6 2] {:ground :land, :unit {:color nil, :type nil}}, [7 3] {:ground :land, :unit {:color nil, :type nil}}, [8 4] {:ground :land, :unit {:color nil, :type nil}}, [9 5] {:ground :land, :unit {:color nil, :type nil}}, [3 0] {:ground :land, :unit {:color nil, :type nil}}, [4 1] {:ground :land, :unit {:color nil, :type nil}}, [5 2] {:ground :water, :unit {:color nil, :type nil}}, [6 3] {:ground :land, :unit {:color nil, :type nil}}, [7 4] {:ground :land, :unit {:color nil, :type nil}}, [8 5] {:ground :land, :unit {:color nil, :type nil}}, [9 6] {:ground :land, :unit {:color nil, :type nil}}, [3 1] {:ground :land, :unit {:color nil, :type nil}}, [4 2] {:ground :water, :unit {:color nil, :type nil}}, [5 3] {:ground :water, :unit {:color nil, :type nil}}, [6 4] {:ground :land, :unit {:color nil, :type nil}}, [7 5] {:ground :land, :unit {:color nil, :type nil}}, [8 6] {:ground :land, :unit {:color nil, :type nil}}, [9 7] {:ground :land, :unit {:color nil, :type nil}}, [2 0] {:ground :land, :unit {:color nil, :type nil}}}))

(def app-state (atom {:marshal {:count 1
                                :rank 10
                                :name "Marshal"}
                      :general {:count 1
                                :rank 9
                                :name "General"}
                      :colonel {:count 2
                                :rank 8
                                :name "Colonel"}
                      :major {:count 3
                              :rank 7
                              :name "Major"}
                      :captain {:count 4
                                :rank 6
                                :name "Captain"}
                      :lieutenant {:count 4
                                   :rank 5
                                   :name "Lieutenant"}
                      :sergeant {:count 4
                                 :rank 4
                                 :name "Sergeant"}
                      :miner {:count 5
                              :rank 3
                              :name "Miner"}
                      :scout {:count 8
                              :rank 2
                              :name "Scout"}
                      :spy {:count 1
                            :rank 1
                            :name "Spy"}
                      :bomb {:count 6
                             :rank \B
                             :name "Bomb"}
                      :flag {:count 1
                             :rank \F
                             :name "Flag"}}))

(defn unit-row [u bgc]
  (let [st #js {:backgroundColor bgc}]
    (dom/tr #js {:className "unit-class" :style st}
            (dom/td nil (:name u))
            (dom/td nil (:count u)))))

(om/root
  app-state
  (fn [app owner]
    (apply dom/table #js {:className "units"}
      (map unit-row
           (sort-by :count (vals app))
           (cycle ["#f90" "#fff"]))))
  (. js/document (getElementById "unit-table")))


(defn render-tile [app pos]
  (let [tile (get app pos)
        unit (:unit tile)]
    (dom/td (if (= :land (:ground tile))
              #js {:className "tile" :style  #js {:backgroundColor "#0c0"}}
              #js {:className "tile" :style  #js {:backgroundColor "#00f"}})
            (if (:color unit)
              (if (= (:color unit) :red)
                (str ((:type unit) @app-state))
                "")
              ""))))

(defn render-row [app positions]
  (apply dom/tr nil
     (map #(render-tile app %) positions)))

(om/root
 board
 (fn [app owner]
   (apply dom/table #js {:className "board"}
          (map #(render-row app %) (partition 10 (sort (keys app))))))
 (. js/document (getElementById "board")))
