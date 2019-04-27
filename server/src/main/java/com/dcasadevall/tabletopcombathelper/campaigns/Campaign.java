package com.dcasadevall.tabletopcombathelper.campaigns;

import javax.annotation.Nullable;

public interface Campaign {
  /**
   * @return A unique identifier for this campaign. This may represent a new campaign to be created.
   */
  @Nullable
  String getCampaignId();

  /**
   * @return Name of the campaign.
   */
  String getName();
}
