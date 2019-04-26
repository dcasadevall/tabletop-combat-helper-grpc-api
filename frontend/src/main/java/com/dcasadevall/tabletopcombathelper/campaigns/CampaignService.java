package com.dcasadevall.tabletopcombathelper.campaigns;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CampaignService extends CampaignServiceGrpc.CampaignServiceImplBase {
  @Inject
  public CampaignService() {
  }

  @Override
  public void listCampaigns(Empty request, StreamObserver<ListCampaignsResponse> responseObserver) {
  }
}
