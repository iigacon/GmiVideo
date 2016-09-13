package com.gomind.gmivideo.view.ulti;

import java.util.UUID;


public class MessageDetailCreated {
    private UUID uuid;

    public MessageDetailCreated(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUUID(){
        return uuid;
    }
}
