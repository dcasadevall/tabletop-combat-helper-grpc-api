package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.server.GrpcServer;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignsService;
import io.grpc.BindableService;

/**
 * Builds and starts a GRPC server used for frontend methods of the Tabletop Combat Helper.
 * This is used by the unity client.
 */
public final class FrontendServer {
  private static final int DEFAULT_PORT = 8000;

  public static void main(String[] args) throws Exception {
    GrpcServer.start(DEFAULT_PORT, new BindableService[] {new CampaignsService()}, args);
  }
}
