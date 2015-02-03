/*
 * Copyright (c) 2014. Green White Solutions LLC. Tashkent.
 */

package uz.greenwhite.slidingmenu.support.v10.service.http_request;

import java.io.InputStream;
import java.io.OutputStream;

public interface Request {

    public void send(OutputStream os) throws Exception;

    public void receive(InputStream is) throws Exception;

}
