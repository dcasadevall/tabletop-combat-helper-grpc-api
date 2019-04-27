package com.dcasadevall.tabletopcombathelper.campaigns;

import dagger.Module;
import dagger.Provides;

/**
 * Module used for the Campaigns system.
 */
@Module
public class CampaignModule {
  @Provides
  CampaignRequestHandler provideCampaignRequestHandler(
      InMemoryCampaignRequestHandler inMemoryCampaignRequestHandler) {
    return inMemoryCampaignRequestHandler;
  }
}
