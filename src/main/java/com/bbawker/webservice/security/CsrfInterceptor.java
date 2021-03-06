//package com.bbawker.webservice.security;
//
//import com.samskivert.mustache.Mustache;
//import com.samskivert.mustache.Template;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.Writer;
//
///**
// * 머스터치에선 바로 Csrf를 사용할 수 없기 때문에 추가
// */
//@Component
//public class CsrfInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public void postHandle
//            (
//                    HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView
//            ) throws Exception
//    {
//        if (modelAndView != null)
//        {
//            // _csrf 를 찾아.
//            modelAndView.addObject("_csrf", new Mustache.Lambda()
//            {
//                public void execute(Template.Fragment frag, Writer out) throws IOException
//                {
//                    // token 에 해당할 경우
//                    if ("token".equals(frag.execute()))
//                    {
//                        // 토큰으로 치환
//                        out.write( ((CsrfToken) req.getAttribute(CsrfToken.class.getName())).getToken() );
//                    }
//                }
//            });
//        }
//
//        super.postHandle(req, res, handler, modelAndView);
//    }
//}
