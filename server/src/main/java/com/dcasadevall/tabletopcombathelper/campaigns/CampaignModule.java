package com.dcasadevall.tabletopcombathelper.campaigns;

import dagger.Module;
import dagger.Provides;

@Module
public class CampaignModule {
  @Provides
  CampaignRequestHandler provideCampaignRequestHandler(
      InMemoryCampaignRequestHandler inMemoryCampaignRequestHandler) {
    return inMemoryCampaignRequestHandler;
  }
}
