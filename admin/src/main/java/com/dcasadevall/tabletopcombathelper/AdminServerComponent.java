package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.tabletopcombathelper.campaigns.CampaignAdminService;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        CampaignModule.class,
    }
)
public interface AdminServerComponent {
  CampaignAdminService getCampaignAdminService();
}
