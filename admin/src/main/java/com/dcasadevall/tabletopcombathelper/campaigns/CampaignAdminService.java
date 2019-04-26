package com.dcasadevall.tabletopcombathelper.campaigns;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CampaignAdminService extends CampaignAdminServiceGrpc.CampaignAdminServiceImplBase {
  private final CampaignRequestHandler campaignRequestHandler;

  @Inject
  public CampaignAdminService(CampaignRequestHandler campaignRequestHandler) {
    this.campaignRequestHandler = campaignRequestHandler;
  }

  @Override
  public void listCampaigns(Empty request, StreamObserver<ListCampaignsResponse> responseObserver) {
    try {
      Collection<Campaign> campaigns = this.campaignRequestHandler.getCampaigns();
      Collection<CampaignProto> campaignProtos =
          campaigns.stream()
              .map(
                  c ->
                      CampaignProto.newBuilder()
                          .setCampaignId(c.getCampaignId())
                          .setName(c.getName())
                          .build())
              .collect(Collectors.toList());

      responseObserver.onNext(
          ListCampaignsResponse.newBuilder().addAllCampaigns(campaignProtos).build());
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

  @Override
  public void saveCampaign(
      SaveCampaignRequest request, StreamObserver<CampaignProto> responseObserver) {
    try {
      Campaign campaign = new ProtobufCampaignAdapter(request.getCampaign());
      String campaignId = this.campaignRequestHandler.saveCampaign(campaign);
      if (campaignId == null) {
        responseObserver.onError(new Exception("Error saving campaign."));
        return;
      }

      responseObserver.onNext(request.getCampaign().toBuilder().setCampaignId(campaignId).build());
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

  @Override
  public void deleteCampaign(
      DeleteCampaignRequest request, StreamObserver<Empty> responseObserver) {
    try {
      boolean success = this.campaignRequestHandler.deleteCampaign(request.getCampaignId());
      if (success) {
        responseObserver.onError(new Exception("Error deleting campaign."));
        return;
      }

      responseObserver.onNext(Empty.getDefaultInstance());
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }
}
