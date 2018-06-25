package com.hydrozagadka;

import java.io.IOException;
import java.util.Map;

public interface Loadable {
    Map<Integer,WaterContainer> load() throws IOException;
    }

