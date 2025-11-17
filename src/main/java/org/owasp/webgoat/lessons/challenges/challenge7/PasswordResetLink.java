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
      // Admin has a fixed reset link
      secureRandom.setSeed(key.hashCode()); // Use a more secure seed
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

}