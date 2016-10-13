package xyz.habbes.democharge.activities.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import xyz.habbes.democharge.R;
import xyz.habbes.democharge.core.models.AccessToken;
import xyz.habbes.democharge.core.models.User;

/**
 * Created by Habbes on 13/10/2016.
 */
public class LoginService {

    /**
     * key used to access the token preference
     */
    public static final String TOKEN_KEY = "token";

    public static final String TOKEN_TTL_KEY = "token_ttl";

    /**
     * key used to access the email preference
     */
    public static final String EMAIL_KEY = "email";

    /**
     * key used to access the name preference
     */
    public static final String NAME_KEY = "name";

    /**
     * key used to access the user id preference
     */
    public static final String USER_ID_KEY = "user_id";

    /**
     * get the preferences files used to store login information
     * @param context
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(
                context.getResources().getString(R.string.login_preference_file),
                Context.MODE_PRIVATE
        );
    }

    /**
     * persists login token and user token on device
     * @param context
     * @param token the access token returned after login
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static void saveLogin(Context context, AccessToken token){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TOKEN_KEY, token.id);
        editor.putLong(TOKEN_TTL_KEY, token.ttl);
        editor.putString(NAME_KEY, token.user.name);
        editor.putString(EMAIL_KEY, token.user.email);
        editor.putString(USER_ID_KEY, token.user.id);
        editor.commit();
    }

    /**
     * gets the access token of the currently logged in user
     * @param context
     * @return access token stored in preferences or null of none found
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static AccessToken getCurrentToken(Context context){
        SharedPreferences pref = getPreferences(context);
        String tokenId = pref.getString(TOKEN_KEY, null);
        if(tokenId == null){
            return null;
        }
        AccessToken token = new AccessToken();
        token.id = tokenId;
        token.ttl = pref.getLong(TOKEN_TTL_KEY, 0);

        String name = pref.getString(NAME_KEY, null);
        String email = pref.getString(EMAIL_KEY, null);
        token.user = new User(name, email);
        token.user.id = pref.getString(USER_ID_KEY, null);

        return token;
    }

    /**
     * destroys login details from the preferences
     * @param context
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static void logout(Context context){
        SharedPreferences pref = getPreferences(context);
        pref.edit().clear().commit();
    }

}
