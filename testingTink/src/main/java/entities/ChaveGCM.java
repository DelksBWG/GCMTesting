package entities;

import javax.crypto.spec.GCMParameterSpec;

public class ChaveGCM {
private GCMParameterSpec gcmTag;
public ChaveGCM(GCMParameterSpec gcmTag) {
	setGcmTag(gcmTag);
}
public GCMParameterSpec getGcmTag() {
	return gcmTag;
}

public void setGcmTag(GCMParameterSpec gcmTag) {
	this.gcmTag = gcmTag;
}
}
