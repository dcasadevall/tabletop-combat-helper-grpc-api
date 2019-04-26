package com.dcasadevall.tabletopcombathelper.campaigns;

import javax.annotation.Nullable;

/**
 * An adapter class that can be used to construct a {@link Campaign} object given a
 * {@link CampaignProto} version of it.
 */
public class ProtobufCampaignAdapter implements Campaign {
  private final CampaignProto campaignProto;

  public ProtobufCampaignAdapter(CampaignProto campaignProto) {
    this.campaignProto = campaignProto;
  }

  @Nullable
  @Override
  public String getCampaignId() {
    return campaignProto.getCampaignId();
  }

  @Override
  public String getName() {
    return campaignProto.getName();
  }
}
