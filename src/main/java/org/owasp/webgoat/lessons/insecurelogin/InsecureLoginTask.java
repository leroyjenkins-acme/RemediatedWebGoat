/*
 * SPDX-FileCopyrightText: Copyright © 2014 WebGoat authors
 * SPDX-License-Identifier: GPL-2.0-or-later
 */
package org.owasp.webgoat.lessons.insecurelogin;

import static org.owasp.webgoat.container.assignments.AttackResultBuilder.failed;
import static org.owasp.webgoat.container.assignments.AttackResultBuilder.success;

import org.owasp.webgoat.container.assignments.AssignmentEndpoint;
import org.owasp.webgoat.container.assignments.AttackResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsecureLoginTask implements AssignmentEndpoint {

  @PostMapping("/InsecureLogin/task")
  @ResponseBody
  public AttackResult completed(@RequestParam String username, @RequestParam String password) {
    boolean isAuthenticated = authenticateUser(username, password);
    if (isAuthenticated) {
      return success(this).build();
    }
    return failed(this).build();
  }

  private boolean authenticateUser(String username, String password) {
    // Simulate a constant time comparison to prevent timing attacks
    try {
      Thread.sleep(50); // Introduce a fixed delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return "CaptainJack".equals(username) && "BlackPearl".equals(password);
  }

  @PostMapping("/InsecureLogin/login")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void login() {
    // only need to exists as the JS needs to call an existing endpoint
  }
}