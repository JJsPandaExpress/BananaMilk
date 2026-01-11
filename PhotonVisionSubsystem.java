package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class PhotonVisionSubsystem {
    private PhotonCamera camera;

    public PhotonVisionSubsystem() {
        // Match this to your PhotonVision camera name
        camera = new PhotonCamera("PhotonCamera");
    }

    public void update() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            PhotonTrackedTarget target = result.getBestTarget();
            double yaw = target.getYaw();
            double pitch = target.getPitch();
            double area = target.getArea();
            double skew = target.getSkew();

            System.out.println("Target detected!");
            System.out.println("Yaw: " + yaw + " Pitch: " + pitch + " Area: " + area + " Skew: " + skew);
        } else {
            System.out.println("No targets found.");
        }
    }
}

