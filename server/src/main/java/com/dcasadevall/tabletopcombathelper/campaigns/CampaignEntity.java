package com.dcasadevall.tabletopcombathelper.campaigns;

import com.google.auto.value.AutoValue;

/**
 * Mutable entity used to internally manipulate campaign objects.
 */
@AutoValue
abstract class CampaignEntity implements Campaign {
  public abstract String getCampaignId();
  public abstract String getName();

  public static CampaignEntity create(String newCampaignId, String newName) {
    return builder()
        .setCampaignId(newCampaignId)
        .setName(newName)
        .build();
  }


  public static Builder builder() {
    return new AutoValue_CampaignEntity.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setCampaignId(String newCampaignId);
    public abstract Builder setName(String newName);
    public abstract CampaignEntity build();
  }
}
