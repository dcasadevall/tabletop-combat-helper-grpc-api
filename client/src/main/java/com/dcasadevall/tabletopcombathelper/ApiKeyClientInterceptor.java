package com.dcasadevall.tabletopcombathelper;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.logging.Logger;

public class ApiKeyClientInterceptor implements ClientInterceptor {
  private final String apiKey;

  private static Logger LOGGER = Logger.getLogger("InfoLogging");

  private static Metadata.Key<String> API_KEY_HEADER =
      Metadata.Key.of("x-api-key", Metadata.ASCII_STRING_MARSHALLER);

  public ApiKeyClientInterceptor(String apiKey) {
    this.apiKey = apiKey;
  }

  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
    LOGGER.info("Intercepted " + method.getFullMethodName());
    ClientCall<ReqT, RespT> call = next.newCall(method, callOptions);

    call =
        new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(call) {
          @Override
          public void start(Listener<RespT> responseListener, Metadata headers) {
            if (apiKey != null && !apiKey.isEmpty()) {
              LOGGER.info("Attaching API Key: " + apiKey);
              headers.put(API_KEY_HEADER, apiKey);
            }
            super.start(responseListener, headers);
          }
        };
    return call;
  }
}
