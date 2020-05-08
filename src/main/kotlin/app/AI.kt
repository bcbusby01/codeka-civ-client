package app

import kotlin.math.abs

// EDIT THIS FILE

class AI() {
    // Change the name that shows up on the graphical display
    // optional, but HIGHLY RECOMMENCED
//    var bronBoard = val arrayOfArray: arrayOf(<Array<Obstacle?>)


    fun getName() = "b.bc"

    // make a move
    // map is the game map
    // players is the list of all the players, containing their resources, cities, armies, workers
    // playerIndex is the index of you player in the players list

    // api functions:
    // doProduce is a function to call to produce something (city, army, worker)
    // doTechnology is a function to call to increase offesive or defensive strength
    // doMoveArmy moves an army from srcPos to dstPos
    // doMoveWorker moves a worker from srcPos to dstPos
    fun isWorker(position: Pair<Int, Int>, players: List<Player>): Boolean {
        for (player in players) {
            for (worker in player.workers) {
                if (worker.position.equals(position)) {
                    return true
                }
            }
        }
        return false
    }

    fun isCity(position: Pair<Int, Int>, players: List<Player>): Boolean {
        for (player in players) {
            for (city in player.cities) {
                if (city.position.equals(position)) {
                    return true
                }
            }
        }
        return false
    }

    fun armyCount(position: Pair<Int, Int>, players: List<Player>, playerIndex: Int): Int {
        var count = 0
        for (army in players[playerIndex].armies) {
            if (army.position.equals(position)) {
                count++
            }
        }
        return count
    }

    fun isEnemyCity(position: Pair<Int, Int>, players: List<Player>, playerIndex: Int): Boolean {
        if (!this.isCity(position, players)) {
            return false
        }
        for (city in players[playerIndex].cities) {
            if (city.position.equals(position)) {
                return false
            }
        }
        return true
    }


    fun isOccupied(position: Pair<Int, Int>, players: List<Player>): Boolean {
        if (isWorker(position, players) || isCity(position, players)) {
            return true
        } else {
            return false
        }
    }

    fun semiWideRange(position: Pair<Int, Int>, players: List<Player>): List<Pair<Int, Int>> {
        var mutableList = mutableListOf<Pair<Int, Int>>()
        if (this.isOnBoard(Pair(position.first + 1, position.second)) && !isOccupied(Pair(position.first + 1, position.second), players)) {
            mutableList.add(Pair(position.first + 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second + 1)) && !isOccupied(Pair(position.first, position.second + 1), players)) {
            mutableList.add(Pair(position.first, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second)) && !isOccupied(Pair(position.first - 1, position.second), players)) {
            mutableList.add(Pair(position.first - 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 1)) && !isOccupied(Pair(position.first, position.second - 1), players)) {
            mutableList.add(Pair(position.first, position.second - 1))
        }


        if (this.isOnBoard(Pair(position.first + 1, position.second + 1)) && !isOccupied(Pair(position.first + 1, position.second + 1), players)) {
            mutableList.add(Pair(position.first + 1, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second + 1)) && !isOccupied(Pair(position.first - 1, position.second + 1), players)) {
            mutableList.add(Pair(position.first - 1, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second - 1)) && !isOccupied(Pair(position.first - 1, position.second - 1), players)) {
            mutableList.add(Pair(position.first - 1, position.second - 1))
        }

        if (this.isOnBoard(Pair(position.first + 1, position.second - 1)) && !isOccupied(Pair(position.first + 1, position.second - 1), players)) {
            mutableList.add(Pair(position.first + 1, position.second - 1))
        }

// -2's

        if (this.isOnBoard(Pair(position.first - 2, position.second)) && !isOccupied(Pair(position.first - 2, position.second), players)) {
            mutableList.add(Pair(position.first - 2, position.second))
        }


        // +2's


        if (this.isOnBoard(Pair(position.first + 2, position.second)) && !isOccupied(Pair(position.first + 2, position.second), players)) {
            mutableList.add(Pair(position.first + 2, position.second))
        }


// 1's


// 0's
        if (this.isOnBoard(Pair(position.first, position.second + 2)) && !isOccupied(Pair(position.first, position.second + 2), players)) {
            mutableList.add(Pair(position.first, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 2)) && !isOccupied(Pair(position.first, position.second - 2), players)) {
            mutableList.add(Pair(position.first, position.second - 2))
        }

        return mutableList
    }

    fun nearbyWideRange(position: Pair<Int, Int>, players: List<Player>): List<Pair<Int, Int>> {
        var mutableList = mutableListOf<Pair<Int, Int>>()


// -2's
        if (this.isOnBoard(Pair(position.first - 2, position.second + 2)) && !isOccupied(Pair(position.first - 2, position.second + 2), players)) {
            mutableList.add(Pair(position.first - 2, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first - 2, position.second + 1)) && !isOccupied(Pair(position.first - 2, position.second + 1), players)) {
            mutableList.add(Pair(position.first - 2, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 2, position.second)) && !isOccupied(Pair(position.first - 2, position.second), players)) {
            mutableList.add(Pair(position.first - 2, position.second))
        }

        if (this.isOnBoard(Pair(position.first - 2, position.second - 1)) && !isOccupied(Pair(position.first - 2, position.second - 1), players)) {
            mutableList.add(Pair(position.first - 2, position.second - 1))
        }

        if (this.isOnBoard(Pair(position.first - 2, position.second - 2)) && !isOccupied(Pair(position.first - 2, position.second - 2), players)) {
            mutableList.add(Pair(position.first - 2, position.second - 2))
        }

        // +2's
        if (this.isOnBoard(Pair(position.first + 2, position.second + 2)) && !isOccupied(Pair(position.first + 2, position.second + 2), players)) {
            mutableList.add(Pair(position.first + 2, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first + 2, position.second + 1)) && !isOccupied(Pair(position.first + 2, position.second + 1), players)) {
            mutableList.add(Pair(position.first + 2, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first + 2, position.second)) && !isOccupied(Pair(position.first + 2, position.second), players)) {
            mutableList.add(Pair(position.first + 2, position.second))
        }

        if (this.isOnBoard(Pair(position.first + 2, position.second - 1)) && !isOccupied(Pair(position.first + 2, position.second - 1), players)) {
            mutableList.add(Pair(position.first + 2, position.second - 1))
        }

        if (this.isOnBoard(Pair(position.first + 2, position.second - 2)) && !isOccupied(Pair(position.first + 2, position.second - 2), players)) {
            mutableList.add(Pair(position.first + 2, position.second - 2))
        }

// 1's
        if (this.isOnBoard(Pair(position.first + 1, position.second + 2)) && !isOccupied(Pair(position.first + 1, position.second + 2), players)) {
            mutableList.add(Pair(position.first + 1, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first + 1, position.second - 2)) && !isOccupied(Pair(position.first + 1, position.second - 2), players)) {
            mutableList.add(Pair(position.first + 1, position.second - 2))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second + 2)) && !isOccupied(Pair(position.first - 1, position.second + 2), players)) {
            mutableList.add(Pair(position.first - 1, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second - 2)) && !isOccupied(Pair(position.first - 1, position.second - 2), players)) {
            mutableList.add(Pair(position.first - 1, position.second - 2))
        }

// 0's
        if (this.isOnBoard(Pair(position.first, position.second + 2)) && !isOccupied(Pair(position.first, position.second + 2), players)) {
            mutableList.add(Pair(position.first, position.second + 2))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 2)) && !isOccupied(Pair(position.first, position.second - 2), players)) {
            mutableList.add(Pair(position.first, position.second - 2))
        }

        return mutableList
    }

    fun moreRoom(position: Pair<Int, Int>, players: List<Player>, playerIndex: Int): Boolean {
        var count = 0
        count = count + this.workerList(position, players, playerIndex).size
        if (this.isOnBoard(Pair(position.first + 1, position.second)) && !isOccupied(Pair(position.first + 1, position.second), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first, position.second + 1)) && !isOccupied(Pair(position.first, position.second + 1), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second)) && !isOccupied(Pair(position.first - 1, position.second), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first, position.second - 1)) && !isOccupied(Pair(position.first, position.second - 1), players)) {
            count++
        }


        if (this.isOnBoard(Pair(position.first + 1, position.second + 1)) && !isOccupied(Pair(position.first + 1, position.second + 1), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second + 1)) && !isOccupied(Pair(position.first - 1, position.second + 1), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second - 1)) && !isOccupied(Pair(position.first - 1, position.second - 1), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first + 1, position.second - 1)) && !isOccupied(Pair(position.first + 1, position.second - 1), players)) {
            count++
        }

// -2's


        if (this.isOnBoard(Pair(position.first - 2, position.second)) && !isOccupied(Pair(position.first - 2, position.second), players)) {
            count++
        }


        // +2's


        if (this.isOnBoard(Pair(position.first + 2, position.second)) && !isOccupied(Pair(position.first + 2, position.second), players)) {
            count++
        }


// 0's
        if (this.isOnBoard(Pair(position.first, position.second + 2)) && !isOccupied(Pair(position.first, position.second + 2), players)) {
            count++
        }

        if (this.isOnBoard(Pair(position.first, position.second - 2)) && !isOccupied(Pair(position.first, position.second - 2), players)) {
            count++
        }

        if (count == 0) {
            return false
        } else {
            return true
        }
    }

    fun workerList(position: Pair<Int, Int>, players: List<Player>, playerIndex: Int): List<BoardUnit> {
        val mutableList = mutableListOf<BoardUnit>()
        for (worker in players[playerIndex].workers) {
            if (worker.position.equals(position)) {
                mutableList.add(worker)
            }
        }
        return mutableList!!
    }

    fun isOnBoard(position: Pair<Int, Int>): Boolean {
        var toReturn = true
        if (position.first < 0 || position.first > 31) {
            toReturn = false
        }
        if (position.second < 0 || position.second > 31) {
            toReturn = false
        }
        return toReturn
    }

    fun surroundingSpaces(boardPiece: BoardUnit, position: Pair<Int, Int>, players: List<Player>): List<Pair<Int, Int>> {
        val mutableList = mutableListOf<Pair<Int, Int>>()
        if (this.isOnBoard(Pair(position.first + 1, position.second)) && !isOccupied(Pair(position.first + 1, position.second), players)) {
            mutableList.add(Pair(position.first + 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second + 1)) && !isOccupied(Pair(position.first, position.second + 1), players)) {
            mutableList.add(Pair(position.first, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second)) && !isOccupied(Pair(position.first - 1, position.second), players)) {
            mutableList.add(Pair(position.first - 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 1)) && !isOccupied(Pair(position.first, position.second - 1), players)) {
            mutableList.add(Pair(position.first, position.second - 1))
        }
        return mutableList
    }

    fun surroundingArmySpaces(boardPiece: BoardUnit, position: Pair<Int, Int>, players: List<Player>): List<Pair<Int, Int>> {
        val mutableList = mutableListOf<Pair<Int, Int>>()
        if (this.isOnBoard(Pair(position.first + 1, position.second))) {
            mutableList.add(Pair(position.first + 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second + 1))) {
            mutableList.add(Pair(position.first, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second))) {
            mutableList.add(Pair(position.first - 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 1))) {
            mutableList.add(Pair(position.first, position.second - 1))
        }
        return mutableList
    }

    fun undefended(boardPiece: BoardUnit, position: Pair<Int, Int>, players: List<Player>, playerIndex: Int): List<Pair<Int, Int>> {
        val mutableList = mutableListOf<Pair<Int, Int>>()
        if (this.isOnBoard(Pair(position.first + 1, position.second)) && this.armyCount(Pair(position.first + 1, position.second) , players, playerIndex) < 1) {
            mutableList.add(Pair(position.first + 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second + 1)) && this.armyCount(Pair(position.first, position.second + 1), players, playerIndex) < 1) {
            mutableList.add(Pair(position.first, position.second + 1))
        }

        if (this.isOnBoard(Pair(position.first - 1, position.second)) && this.armyCount(Pair(position.first - 1, position.second), players, playerIndex) < 1) {
            mutableList.add(Pair(position.first - 1, position.second))
        }

        if (this.isOnBoard(Pair(position.first, position.second - 1)) && this.armyCount(Pair(position.first, position.second - 1), players, playerIndex) < 1) {
            mutableList.add(Pair(position.first, position.second - 1))
        }


        return mutableList
    }

    fun closeEnough(position: Pair<Int, Int>, obj: BoardObject, players: List<Player>, playerIndex: Int): Boolean {
        for (city in players[playerIndex].cities) {
            if (obj.distanceTo(city) < 2) {
                return true
            }
        }
        return false

    }

    fun isOn(position: Pair<Int, Int>, lst: MutableList<Pair<Int, Int>>): Boolean {
        val iterator = lst.listIterator()
        for (loc in iterator) {
            if (loc.equals(position)) {
                return true
            }
        }
        return false
    }

    fun doMove(
            map: GameMap,
            players: List<Player>,
            playerIndex: Int,
            doProduce: (type: ProductionType, location: Pair<Int, Int>) -> Boolean,
            doTechnology: (type: TechnologyType) -> Boolean,
            doMoveArmy: (srcPos: Pair<Int, Int>, dstPos: Pair<Int, Int>) -> Boolean,
            doMoveWorker: (srcPos: Pair<Int, Int>, dstPos: Pair<Int, Int>) -> Boolean
    ) {

        var foodNeeded = players[playerIndex].armies.size + players[playerIndex].workers.size
        var foodNextRound = (players[playerIndex].resources[ResourceType.Food]!! * 4) /3
        var foodRemaining = foodNextRound - foodNeeded


        var planEscape = mutableListOf<Pair<Int, Int>>()
        var worldDomination = mutableListOf<Pair<Int, Int>>()

        if (players[playerIndex].resources[ResourceType.Production]!! >= 8) {
            for (city in players[playerIndex].cities) {
                var total = 0
                if (players[playerIndex].resources[ResourceType.Production]!! >= 8 && this.moreRoom(city.position, players, playerIndex) && this.workerList(city.position, players, playerIndex).size < 1) {
                    doProduce(ProductionType.Worker, city.position)
                }
            }
        }

        for (worker in players[playerIndex].workers) {
            if (this.isOccupied(worker.position, players)) {
                var list = this.surroundingSpaces(worker, worker.position, players)
                var mList = mutableListOf<Pair<Int, Int>>()
                val iterator = list.listIterator()
                for (location in iterator) {
                    if (this.closeEnough(location, worker, players, playerIndex) && !this.isOn(location, planEscape)) {
                        mList.add(location)
                    }
                }
                if (mList.size > 0) {
                    doMoveWorker(worker.position, mList.get(0))
                    planEscape.add(mList.get(0))

                }
            }
        }

        if (players[playerIndex].resources[ResourceType.Production]!! >= 24) {
            for (worker in players[playerIndex].workers) {
                var wide = this.nearbyWideRange(worker.position, players)
                if (wide.size > 0) {
                    val iterator = wide.listIterator()
                    for (loc in wide) {
                        var semi = this.nearbyWideRange(loc, players)
                        var closeToCity = false
                        for (unit in semi) {
                            if (this.isCity(unit, players) || this.isOn(unit, worldDomination)) {
                                closeToCity = true
                            }
                        }
                        for (city in players[playerIndex].cities) {
                            if (abs(loc.first - city.position.first) + abs(loc.second - city.position.second) <= 3) {
                                closeToCity = true
                            }

                        }
                        val iterator2 = worldDomination.listIterator()
                        for (city in iterator2) {
                            if (abs(loc.first - city.first) + abs(loc.second - city.second) <= 3) {
                                closeToCity = true
                            }

                        }
                        if (!closeToCity && !this.isOn(loc, worldDomination) && players[playerIndex].resources[ResourceType.Production]!! >= 24) {
                            worldDomination.add(loc)
                            doProduce(ProductionType.City, loc)
                        }
                    }
                }
            }
        }


        if (players[playerIndex].resources[ResourceType.Production]!! >= 8) {
            for (city in players[playerIndex].cities) {
                if (foodRemaining > 0) {
                    if (players[playerIndex].resources[ResourceType.Production]!! >= 8 && (players[playerIndex].cities.size + players[playerIndex].workers.size) > players[playerIndex].armies.size + 5) {
                        doProduce(ProductionType.Army, city.position)
                        foodRemaining--
                    }
                }
            }
        }

        var defensePlan = mutableListOf<Pair<Int, Int>>()


        var count = 0
        for (army in players[playerIndex].armies) {
            count++
            if (this.isCity(army.position, players) && this.armyCount(army.position, players, playerIndex) < 3) {
                var count = 0
                count++
            } else {
                var possible = this.surroundingArmySpaces(army, army.position, players)
                val iterator = possible.listIterator()
                for (space in iterator) {
                    if (this.isEnemyCity(space, players, playerIndex)) {
                        doMoveArmy(army.position, space)
                    }
                }
                var mutablelist = this.undefended(army, army.position, players, playerIndex)
                if (mutablelist.size > count%4) {
                    doMoveArmy(army.position, mutablelist.get(count%4))
                }

            }

        }







        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }

        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }
        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
            doTechnology(TechnologyType.Defense)
            doTechnology(TechnologyType.Offense)
        }



//        for (city in players[playerIndex].cities) {
//            var workUnits = this.workerList(city.position, players, playerIndex)
//            if (workUnits.size!! > 0) {
//                val workSample = workUnits.get(0)
//                var list = this.surroundingSpaces(workSample, workSample.position, players)
//                if (list.size!! > 0 && workUnits.size!! > 1) {
//                    for (i in workUnits.indices) {
//                        if (i > 0) {
//                            var newList = this.surroundingSpaces(workSample, workSample.position, players)
//
//                            if (newList.size!! > 0) {
//                                doMoveWorker(workUnits.get(i).position, list.get(0))
//                            }
//                        }
//                    }
//                    var newList = this.surroundingSpaces(workSample, workSample.position, players)
//                    if (newList.size!! > 0) {
//                        doMoveWorker(workSample.position, list.get(0))
//                    }
//                }
//            }
//        }

//        if (players[playerIndex].resources[ResourceType.Production]!! >= 8) {
//            for (city in players[playerIndex].cities) {
//                if (players[playerIndex].resources[ResourceType.Production]!! >= 8 && this.workerList(city.position, players, playerIndex).size!! <= 0) {
//                    doProduce(ProductionType.Worker, city.position)
//                }
//
//            }
//        }
//        if (players[playerIndex].resources[ResourceType.Production]!! >= 8 && players[playerIndex].workers.size < players[playerIndex].cities.size * 10) {
//            doProduce(ProductionType.Worker, players[playerIndex].cities[0].position)
//
//        }


//        var left = true
//        var top = false
//
//        if (players[playerIndex].cities[0].position.first > 1) {
//            left = false
//        }
//        if (players[playerIndex].cities[0].position.second > 1) {
//            top = true
//        }
//
//
//        if (left && top) {
//
//
//        }

//        if (players[playerIndex].workers.size > 3 && players[playerIndex].resources[ResourceType.Production]!! >= 8 && players[playerIndex].workers.size + players[playerIndex].cities.size >= (2 * players[playerIndex].armies.size)) {
//            doProduce(ProductionType.Army, players[playerIndex].cities[0].position)
//        }
//
//        if (players[playerIndex].resources[ResourceType.Production]!! >= 8 && players[playerIndex].workers.size < players[playerIndex].cities.size * 10) {
//            doProduce(ProductionType.Worker, players[playerIndex].cities[0].position)
//
//        }
//
//
//        if (players[playerIndex].resources[ResourceType.Trade]!! >= 20 && players[playerIndex].armies.size < 4) {
//            doTechnology(TechnologyType.Defense)
//        }
//        if (players[playerIndex].resources[ResourceType.Trade]!! >= 40) {
//            doTechnology(TechnologyType.Defense)
//            doTechnology(TechnologyType.Offense)
//        }
//
//        if (left && top) {
//
//
//
//            for (city in players[playerIndex].cities) {
//
//            }
//
//
//
//
//            var i = players[playerIndex].cities.size * 12
//
//            for (worker in players[playerIndex].workers) {
//
//                if (worker.distanceTo(players[playerIndex].cities[players[playerIndex].cities.size - 1].position) <= 13) {
//                    if (i % 2 == 1 && worker.isValidMove(Pair(worker.position.first, worker.position.second - 1))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first, worker.position.second - 1))
////                        bronBoard[worker.position.first][worker.position.second] = worker.position.TyleType
//                    } else if (worker.isValidMove(Pair(worker.position.first + 1, worker.position.second))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first + 1, worker.position.second))
////                        bronBoard[worker.position.first][worker.position.second] = worker.position.TyleType
//                    }
//                    i--
//                }
//            }
//
//            var m = players[playerIndex].cities.size * 13
//            // another example: move all armies to the right one unit(why not?)
//            var num = 0
//            for (army in players[playerIndex].armies) {
//
//
//                if (army.position.equals(players[playerIndex].cities[0].position)) {
//                    num++
//                }
//                if (army.distanceTo(players[playerIndex].cities[0].position) > 3 && players[playerIndex].resources[ResourceType.Production]!! >= 24) {
//                    doProduce(ProductionType.City, army.position)
//                } else {
//                    if (m % 2 == 1 && army.isValidMove(Pair(army.position.first + 1, army.position.second))) {
//                        doMoveArmy(army.position, Pair(army.position.first + 1, army.position.second))
//                    } else if (army.isValidMove(Pair(army.position.first, army.position.second - 1))) {
//                        doMoveArmy(army.position, Pair(army.position.first, army.position.second - 1))
//                    }
//                    m--
//
//
//                }
//            }
//        } else if (!left && top) {
//            var i = players[playerIndex].cities.size * 12
//            for (worker in players[playerIndex].workers) {
//
//                if (worker.distanceTo(players[playerIndex].cities[players[playerIndex].cities.size - 1].position) <= 13) {
//                    if (i % 2 == 1 && worker.isValidMove(Pair(worker.position.first, worker.position.second - 1))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first, worker.position.second - 1))
//                    } else if (worker.isValidMove(Pair(worker.position.first - 1, worker.position.second))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first - 1, worker.position.second))
//                    }
//                    i--
//                }
//            }
//
//            var num = 0
//            var m = players[playerIndex].cities.size * 13
//            for (army in players[playerIndex].armies) {
//                if (army.position.equals(players[playerIndex].cities[0].position)) {
//                    num++
//                }
//
//                if (army.distanceTo(players[playerIndex].cities[0].position) > 3 && players[playerIndex].resources[ResourceType.Production]!! >= 24) {
//                    doProduce(ProductionType.City, army.position)
//                } else {
//                    if (m % 2 == 0 && army.isValidMove(Pair(army.position.first, army.position.second - 1))) {
//                        doMoveArmy(army.position, Pair(army.position.first, army.position.second - 1))
//                    } else if (army.isValidMove(Pair(army.position.first - 1, army.position.second))) {
//                        doMoveArmy(army.position, Pair(army.position.first - 1, army.position.second))
//                    }
//                    m--
//                }
//
//            }
//        } else if (!left && !top) {
//            var i = players[playerIndex].cities.size * 12
//            for (worker in players[playerIndex].workers) {
//                if (worker.distanceTo(players[playerIndex].cities[players[playerIndex].cities.size - 1].position) <= 13) {
//                    if (i % 2 == 1 && worker.isValidMove(Pair(worker.position.first, worker.position.second + 1))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first, worker.position.second + 1))
//                    } else if (worker.isValidMove(Pair(worker.position.first - 1, worker.position.second))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first - 1, worker.position.second))
//                    }
//                }
//                i--
//            }
//
//            var num = 0
//            var m = players[playerIndex].cities.size * 13
//            for (army in players[playerIndex].armies) {
//                if (army.position.equals(players[playerIndex].cities[0].position)) {
//                    num++
//                }
//
//                if (army.distanceTo(players[playerIndex].cities[0].position) > 3 && players[playerIndex].resources[ResourceType.Production]!! >= 24) {
//                    doProduce(ProductionType.City, army.position)
//                } else {
//                    if (m % 2 == 0 && army.isValidMove(Pair(army.position.first, army.position.second + 1))) {
//                        doMoveArmy(army.position, Pair(army.position.first, army.position.second + 1))
//                    } else if (army.isValidMove(Pair(army.position.first - 1, army.position.second))) {
//                        doMoveArmy(army.position, Pair(army.position.first - 1, army.position.second))
//                    }
//                    m--
//                }
//
//            }
//        } else {
//            var i = players[playerIndex].cities.size * 12
//            var k = 13
//            for (worker in players[playerIndex].workers) {
//
//                if (worker.distanceTo(players[playerIndex].cities[players[playerIndex].cities.size - 1].position) <= k) {
//                    if (i % 2 == 1 && worker.isValidMove(Pair(worker.position.first, worker.position.second + 1))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first, worker.position.second + 1))
//                    } else if (worker.isValidMove(Pair(worker.position.first + 1, worker.position.second))) {
//                        doMoveWorker(worker.position, Pair(worker.position.first + 1, worker.position.second))
//                    }
//                    i--
//                    k--
//                }
//            }
//
//            var num = 0
//            var m = players[playerIndex].cities.size * 13
//            for (army in players[playerIndex].armies) {
//                if (army.position.equals(players[playerIndex].cities[0].position)) {
//                    num++
//                }
//
//                if (army.distanceTo(players[playerIndex].cities[0].position) > 3 && players[playerIndex].resources[ResourceType.Production]!! >= 24) {
//                    doProduce(ProductionType.City, army.position)
//                } else {
//                    if (m % 2 == 0 && army.isValidMove(Pair(army.position.first, army.position.second + 1))) {
//                        doMoveArmy(army.position, Pair(army.position.first, army.position.second + 1))
//                    } else if (army.isValidMove(Pair(army.position.first + 1, army.position.second))) {
//                        doMoveArmy(army.position, Pair(army.position.first + 1, army.position.second))
//                    }
//                    m--
//
//                }
//            }
//        }


    }
}

