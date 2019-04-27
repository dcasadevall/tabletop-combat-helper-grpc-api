package com.dcasadevall.tabletopcombathelper.grpc.service;

import com.dcasadevall.tabletopcombathelper.campaigns.CampaignModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        CampaignModule.class,
    }
)
public interface GrpcServiceComponent {
  CampaignService getCampaignService();
}
