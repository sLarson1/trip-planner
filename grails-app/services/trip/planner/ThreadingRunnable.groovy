package trip.planner

/**
 * Created by Mr. Sean on 3/8/2017.
 */
class ThreadingRunnable implements Runnable{

    def duration

    ThreadingRunnable(int val){
        duration = val
    }

    @Override
    void run() {
        println("\nEntered run()!")

        def startTime = System.currentTimeSeconds()
        def countSeconds
        while((startTime + duration) < System.currentTimeSeconds()){
            countSeconds++
            println(countSeconds)
        }

        println("${this.toString()} is done!")
    }
}
