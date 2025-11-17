/*
 * SPDX-FileCopyrightText: Copyright © 2025 WebGoat authors
 * SPDX-License-Identifier: GPL-2.0-or-later
 */
package org.owasp.webgoat.lessons.openredirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Provides a real 302 redirect for experimentation separate from assignment scoring.
 */
@Controller
public class OpenRedirectRealRedirect {

  @GetMapping("/OpenRedirect/realRedirect")
  public ModelAndView real(@RequestParam("url") String url) {
    // Validate the URL to prevent open redirect vulnerabilities
    if (isValidUrl(url)) {
      return new ModelAndView("redirect:" + encodeUrl(url));
    } else {
      return new ModelAndView("error"); // Redirect to an error page or handle the error appropriately
    }
  }

  private boolean isValidUrl(String url) {
    // Implement URL validation logic here
    // For example, check if the URL is within the same domain or matches a whitelist
    return url.startsWith("https://trusted-domain.com");
  }

  private String encodeUrl(String url) {
    // Encode the URL to prevent XSS
    return org.springframework.web.util.UriUtils.encode(url, "UTF-8");
  }
}