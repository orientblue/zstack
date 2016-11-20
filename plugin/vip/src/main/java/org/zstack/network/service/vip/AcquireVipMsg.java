package org.zstack.network.service.vip;

import org.zstack.header.message.NeedReplyMessage;
import org.zstack.header.network.l3.L3NetworkInventory;

/**
 * Created by xing5 on 2016/11/19.
 */
public class AcquireVipMsg extends NeedReplyMessage implements VipMessage {
    private String vipUuid;
    private L3NetworkInventory peerL3Network;
    private String networkServiceProviderType;

    public AcquireVipStruct toAcquireVipStruct() {
        AcquireVipStruct s = new AcquireVipStruct();
        s.setPeerL3Network(getPeerL3Network());
        s.setNetworkServiceProviderType(getNetworkServiceProviderType());
        s.setVipUuid(getVipUuid());
        return s;
    }

    @Override
    public String getVipUuid() {
        return vipUuid;
    }

    public void setVipUuid(String vipUuid) {
        this.vipUuid = vipUuid;
    }

    public L3NetworkInventory getPeerL3Network() {
        return peerL3Network;
    }

    public void setPeerL3Network(L3NetworkInventory peerL3Network) {
        this.peerL3Network = peerL3Network;
    }

    public String getNetworkServiceProviderType() {
        return networkServiceProviderType;
    }

    public void setNetworkServiceProviderType(String networkServiceProviderType) {
        this.networkServiceProviderType = networkServiceProviderType;
    }
}
