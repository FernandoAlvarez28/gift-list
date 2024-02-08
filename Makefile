all: build-docker-image run-docker-image

build-docker-image:
	docker build -t giftlist:latest .

build-docker-image-multiplatform:
	docker buildx create --use;
	docker buildx build --platform linux/arm64/v8,linux/amd64 -t giftlist:latest .

run-docker-image:
	docker-compose up giftlist
