package trip.planner

/**
 * Created by Mr. Sean on 3/9/2017.
 */
class SomeClass {

    def test(){
        println("starting......")
        Thread.start({new ThreadingRunnable(100)})
        Thread.start({new ThreadingRunnable(100)})
        println("\ncomplete")
    }
}
