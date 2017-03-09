package com.apppartner.androidtest.bus;

import com.squareup.otto.Bus;

/**
 * Created by mr.alexander on 2/22/17.
 */

public class BusProvider {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private static final Bus BUS = new Bus();

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================
    public static Bus getInstance() {
        return BUS;
    }
}
