package com.dcasadevall.tabletopcombathelper.campaigns;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * Handles requests related to campaigns.
 */
@Singleton
public interface CampaignRequestHandler {
  /**
   * @return A collection of {@link Campaign} objects existing in the store.
   */
  Collection<Campaign> getCampaigns();

  /**
   * @param campaign The campaign to save in the store
   * @return The campaign id of the campaign saved if successful. Null otherwise..
   */
  @Nullable
  String saveCampaign(Campaign campaign);

  /**
   * @param campaignId The id of the campaign to delete.
   * @return True if successfully deleted. False otherwise.
   */
  boolean deleteCampaign(String campaignId);
}
