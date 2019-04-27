package com.dcasadevall.tabletopcombathelper.grpc;

import com.dcasadevall.server.GrpcServer;
import com.dcasadevall.tabletopcombathelper.grpc.service.DaggerGrpcServiceComponent;
import io.grpc.BindableService;

/** Builds and starts a GRPC server used for all methods of the Tabletop Combat Helper. */
public final class TabletopCombatHelperServer {
  private static final int DEFAULT_PORT = 8000;

  public static void main(String[] args) throws Exception {

    GrpcServer.start(
        DEFAULT_PORT,
        new BindableService[] {DaggerGrpcServiceComponent.create().getCampaignService()},
        args);
  }
}
