package com.dcasadevall.tabletopcombathelper.campaigns;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InMemoryCampaignRequestHandler implements CampaignRequestHandler {
  @Inject
  public InMemoryCampaignRequestHandler() {
  }

  @Override
  public Collection<Campaign> getCampaigns() {
    return null;
  }

  @Override
  @Nullable
  public String saveCampaign(Campaign campaign) {
    return null;
  }

  @Override
  public boolean deleteCampaign(String campaignId) {
    return false;
  }
}
