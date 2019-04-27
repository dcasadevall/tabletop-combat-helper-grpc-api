package com.dcasadevall.tabletopcombathelper.campaigns;

import com.google.common.base.Strings;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/** In Memory implementation {@link CampaignRequestHandler} used for testing. Not thread safe. */
@Singleton
public class InMemoryCampaignRequestHandler implements CampaignRequestHandler {
  private Logger logger = Logger.getLogger(InMemoryCampaignRequestHandler.class.getName());
  private HashMap<String, CampaignEntity> campaigns = new HashMap<>();

  @Inject
  public InMemoryCampaignRequestHandler() {}

  @Override
  public Collection<Campaign> getCampaigns() {
    return new ArrayList<>(campaigns.values());
  }

  @Override
  @Nullable
  public String saveCampaign(Campaign campaign) {
    if (Strings.isNullOrEmpty(campaign.getCampaignId())) {
      String campaignId = UUID.randomUUID().toString();
      CampaignEntity campaignEntity = CampaignEntity.create(campaignId, campaign.getName());
      campaigns.put(campaignId, campaignEntity);

      return campaignId;
    }

    if (!campaigns.containsKey(campaign.getCampaignId())) {
      logger.severe(String.format("Campaign not found: %s", campaign.getCampaignId()));
      return null;
    }

    campaigns.put(
        campaign.getCampaignId(),
        CampaignEntity.builder()
            .setName(campaign.getName())
            .setCampaignId(campaign.getCampaignId())
            .build());
    return campaign.getCampaignId();
  }

  @Override
  public boolean deleteCampaign(String campaignId) {
    Preconditions.checkNotNull(campaignId);

    if (!campaigns.containsKey(campaignId)) {
      logger.severe(String.format("Campaign not found: %s", campaignId));
      return false;
    }

    campaigns.remove(campaignId);
    return true;
  }
}
