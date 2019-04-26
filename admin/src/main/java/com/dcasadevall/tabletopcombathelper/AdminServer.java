package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.server.GrpcServer;
import io.grpc.BindableService;

/** Builds and starts a GRPC server used for admin methods of the Tabletop Combat Helper. */
public final class AdminServer {
  private static final int DEFAULT_PORT = 8000;

  public static void main(String[] args) throws Exception {

    GrpcServer.start(
        DEFAULT_PORT,
        new BindableService[] {DaggerAdminServerComponent.create().getCampaignAdminService()},
        args);
  }
}
