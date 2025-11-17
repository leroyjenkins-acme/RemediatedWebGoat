/*
 * SPDX-FileCopyrightText: Copyright © 2017 WebGoat authors
 * SPDX-License-Identifier: GPL-2.0-or-later
 */
package org.owasp.webgoat.lessons.challenges.challenge7;

import java.security.SecureRandom;

/**
 * WARNING: DO NOT CHANGE FILE WITHOUT CHANGING .git contents
 */
public class PasswordResetLink {

  public String createPasswordReset(String username, String key) {
    SecureRandom secureRandom = new SecureRandom();
    if (username.equalsIgnoreCase("admin")) {
      // Admin has a fix reset link
      secureRandom.setSeed(key.length());
    }
    return scramble(secureRandom, scramble(secureRandom, scramble(secureRandom, MD5.getHashString(username))));
  }

  public static String scramble(SecureRandom secureRandom, String inputString) {
    char[] a = inputString.toCharArray();
    for (int i = 0; i < a.length; i++) {
      int j = secureRandom.nextInt(a.length);
      char temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }
    return new String(a);
  }

  public static void main(String[] args) {
    if (args == null || args.length != 2) {
      System.out.println("Need a username and key");
      return;
    }
    String username = args[0];
    String key = args[1];
    System.out.println("Generation password reset link for " + username);
    System.out.println(
        "Created password reset link: "
            + new PasswordResetLink().createPasswordReset(username, key));
  }
}