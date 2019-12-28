# Thread   

In Android, you can categorize all threading components into two basic categories:  

- Threads that are attached to an `activity/fragment`: These threads are tied to the lifecycle of the activity/fragment and are terminated as soon as the activity/fragment is destroyed.  

- Threads that are not attached to any `activity/fragment`: These threads can continue to run beyond the lifetime of the activity/fragment (if any) from which they were spawned.  


**AsyncTask** is the most basic Android component for threading.  

Sample usage:  
```java
public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new MyTask().execute(url);
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            return doSomeWork(url);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // do something with result 
        }
    }
}
```

**Service** is a component that is useful for performing long (or potentially long) operations without any UI.  
Service runs in the main thread of its hosting process; the service does not create its own thread and does not run in a separate process unless you specify otherwise.  

Sample usage:  
```java
public class ExampleService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doSomeLongProccesingWork();
        stopSelf();

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
```

With Service, it is your responsibility to stop it when its work is complete by calling either the `stopSelf()` or the `stopService()` method.  

Like Service, **IntentService** runs on a separate thread, and stops itself automatically after it completes its work.  
IntentService is usually used for short tasks that don’t need to be attached to any UI.  

Sample usage:  
```java
public class ExampleService extends IntentService {
    
    public ExampleService() {
        super("ExampleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        doSomeShortWork();
    }
}
```

**Tips**  

Service may be a better fit for this use case since it isn’t attached to any activity.  
It will therefore be able to continue with the network call even after the activity is destroyed.  
Plus, since the response from the server is not needed, a service wouldn’t be limiting here, either.  
However, since a service will begin running on the UI thread, you will still need to manage threading yourself.  
You will also need to make sure that the service is stopped once the network call is complete.  
This would require more effort than should be necessary for such a simple action.  

**IntentService**  
This, in my opinion, would be the best option.  
Since IntentService doesn’t attach to any activity and it runs on a non-UI thread, it serves our needs perfectly here. Moreover, IntentService stops itself automatically, so there is no need to manually manage it, either.  











