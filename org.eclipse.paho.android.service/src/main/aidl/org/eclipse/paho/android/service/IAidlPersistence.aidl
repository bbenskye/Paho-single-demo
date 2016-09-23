// IAidlPersistence.aidl
package org.eclipse.paho.android.service;
// Declare any non-default types here with import statements

interface IAidlPersistence {

  int getPersistenceType();
  String getAbsFileDir ();

}
