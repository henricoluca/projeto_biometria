package com.projeto_biometria.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class CameraUtil {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static String captureImage(String filePath) {
        VideoCapture camera = new VideoCapture(0); // 0 é o índice da webcam
        if (!camera.isOpened()) {
            System.out.println("Erro ao abrir a câmera.");
            return null;
        }

        Mat frame = new Mat();
        if (camera.read(frame)) {
            Imgcodecs.imwrite(filePath, frame);
            System.out.println("Imagem capturada e salva em: " + filePath);
        } else {
            System.out.println("Falha ao capturar a imagem.");
        }

        camera.release();
        return filePath;
    }
}
