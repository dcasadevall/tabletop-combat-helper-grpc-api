package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.tabletopcombathelper.campaigns.CampaignModule;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignService;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        CampaignModule.class,
    }
)
public interface FrontendServerComponent {
  CampaignService getCampaignService();
}
