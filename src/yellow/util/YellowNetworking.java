package yellow.util;

import arc.func.*;
import arc.util.*;
import arc.util.serialization.*;

public class YellowNetworking{

    private static final JsonReader reader = new JsonReader();

    /** Do NOT overuse this! */
    public static void repoReleases(String repo, Cons<JsonValue> cons){
        Http.get("https://api.github.com/repos/" + repo + "/releases", c -> {
            String json = c.getResultAsString();

            cons.get(reader.parse(json));
        }, Log::err);
    }
}
