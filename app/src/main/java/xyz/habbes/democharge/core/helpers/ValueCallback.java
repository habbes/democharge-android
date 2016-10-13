package xyz.habbes.democharge.core.helpers;

/**
 * generic callback used to pass a value
 * from an asynchronous method
 * Created by Habbes on 13/10/2016.
 */
public interface ValueCallback<T> {
    /**
     * method to call when the value is ready
     * @param value
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    void onValue(T value);

    /**
     * method to call if an error occurs
     * @param error
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    void onError(Object error);
}
