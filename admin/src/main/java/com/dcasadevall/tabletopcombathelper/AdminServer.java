package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.server.GrpcServer;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignsAdminService;
import io.grpc.BindableService;

/** Builds and starts a GRPC server used for admin methods of the Tabletop Combat Helper. */
public final class AdminServer {
  private static final int DEFAULT_PORT = 8002;

  public static void main(String[] args) throws Exception {
    GrpcServer.start(DEFAULT_PORT, new BindableService[] {new CampaignsAdminService()}, args);
  }
}
