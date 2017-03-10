package trip.planner

/**
 * Created by Mr. Sean on 3/9/2017.
 */
class ThreadTester {

    def test(){
        Thread.start(new ThreadingRunnable(100))
        Thread.start(new ThreadingRunnable(100))
    }
}
