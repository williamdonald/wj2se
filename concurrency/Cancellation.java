@ThreadSafe
public class PrimeGenerator implements Runnable {
     @GuardedBy("this")
     private final List<BigInteger> primes
             = new ArrayList<BigInteger>();
     private  volatile boolean cancelled;

     public void run() {
         BigInteger p = BigInteger.ONE;
         while (!cancelled ) {
             p = p.nextProbablePrime();
             synchronized (this) {
                 primes.add(p);
             }
         }
     }

     public void cancel() { cancelled = true;  }

     public synchronized List<BigInteger> get() {
         return new ArrayList<BigInteger>(primes);
     }
}

class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /*  Allow thread to exit  */
        }
    }
    public void cancel() { interrupt(); }
}

public Task getNextTask(BlockingQueue<Taskgt; queue) {
    boolean interrupted = false;
    try {
        while (true) {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                interrupted = true;
                // fall through and retry
            }
        }
    } finally {
        if (interrupted)
            Thread.currentThread().interrupt();
    }
}

//Cancelling a Task Using Future.

public static void timedRun(Runnable r,
                            long timeout, TimeUnit unit)
                            throws InterruptedException {
    Future<?> task = taskExec.submit(r);
    try {
        task.get(timeout, unit);
    } catch (TimeoutException e) {
        // task will be cancelled below
    } catch (ExecutionException e) {
        // exception thrown in task; rethrow
        throw launderThrowable(e.getCause());
    } finally {
        // Harmless if task already completed
        task.cancel(true);  // interrupt if running
    }
}



