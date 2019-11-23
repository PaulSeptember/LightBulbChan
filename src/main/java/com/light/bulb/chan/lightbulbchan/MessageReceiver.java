package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.LBCMessage;

public interface MessageReceiver {
    void OnMessage(LBCMessage message);
}
